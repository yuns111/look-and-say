const {chan, go, put, CLOSED} = require("js-csp");

function next(i) {
  let o = chan();
  go(function* () {
    let prev = yield i;
    let count = 1;
    for (let value = yield i; value !== CLOSED; value = yield i) {
      if (value === prev) {
        count++;
      } else {
        yield put(o, count);
        yield put(o, prev);
        prev = value;
        count = 1;
      }
    }
    yield put(o, count);
    yield put(o, prev);
    o.close();
  });
  return o;
}