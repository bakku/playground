class Pledge {
  private thenCallbacks: Array<(val: any) => any> = [];

  constructor(action: (resolve: (value: any) => any) => void) {
    setImmediate(() => action(this.onResolve.bind(this)));
  }

  static create(action: (resolve: (value: any) => any) => any): Pledge {
    return new Pledge(action);
  }

  then(callback: (value: any) => any): Pledge {
    this.thenCallbacks.push(callback);

    return this;
  }

  onResolve(value: any) {
    let resolvedValue = value;

    for (const callback of this.thenCallbacks) {
      resolvedValue = callback(resolvedValue);
    }
  }
}

Pledge.create(resolve => resolve(5))
  .then(val => val + 1)
  .then(val => `result is: ${val}`)
  .then(msg => console.log(msg));