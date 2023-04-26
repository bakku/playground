package main

import (
	"context"
	"errors"
	"fmt"
	"sync"
	"time"
)

type Worker struct {
	mutex      *sync.Mutex
	running    bool
	amount     int
	ctx        context.Context
	cancelFunc context.CancelFunc
}

func NewWorker(amount int) *Worker {
	return &Worker{
		mutex:   &sync.Mutex{},
		running: false,
		amount:  amount,
	}
}

func (w *Worker) Start() error {
	w.mutex.Lock()
	defer w.mutex.Unlock()

	if w.running {
		return errors.New("worker is already running")
	}

	ctx, cancel := context.WithCancel(context.Background())

	w.ctx = ctx
	w.cancelFunc = cancel
	w.running = true

	for i := 0; i < w.amount; i++ {
		go func(ctx context.Context) {
			for {
				select {
				case <-ctx.Done():
					fmt.Println("Stopping...")
					return
				default:
					fmt.Println("Doing work")
					time.Sleep(time.Second)
				}
			}
		}(w.ctx)
	}

	return nil
}

func (w *Worker) Stop() error {
	w.mutex.Lock()
	defer w.mutex.Unlock()

	if w.running {
		w.cancelFunc()
		w.running = false
		return nil
	} else {
		return errors.New("worker is not running")
	}
}
