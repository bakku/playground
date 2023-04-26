package main

import (
	"fmt"
	"log"
	"net/http"
)

func main() {
	worker := NewWorker(2)

	mux := http.NewServeMux()
	mux.Handle("/start", &StartHandler{worker})
	mux.Handle("/stop", &StopHandler{worker})

	err := http.ListenAndServe(":3006", mux)
	log.Fatalln(err)
}

type StartHandler struct {
	worker *Worker
}

func (h *StartHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	err := h.worker.Start()

	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)

		_, err = fmt.Fprintf(w, "error: %v", err)
		if err != nil {
			fmt.Printf("start handler: error while writing error to response: %v", err)
		}
	} else {
		w.WriteHeader(http.StatusOK)

		_, err = fmt.Fprint(w, "started")
		if err != nil {
			fmt.Printf("start handler: error while writing success to response: %v", err)
		}
	}
}

type StopHandler struct {
	worker *Worker
}

func (h *StopHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
	err := h.worker.Stop()

	if err != nil {
		w.WriteHeader(http.StatusInternalServerError)

		_, err = fmt.Fprintf(w, "error: %v", err)
		if err != nil {
			fmt.Printf("stop handler: error while writing error to response: %v", err)
		}
	} else {
		w.WriteHeader(http.StatusOK)

		_, err = fmt.Fprint(w, "stopped")
		if err != nil {
			fmt.Printf("stop handler: error while writing success to response: %v", err)
		}
	}
}
