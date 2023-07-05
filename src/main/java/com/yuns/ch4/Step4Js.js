function ant(n) {
  let s = iter([1]);

  for (let i = 0; i < n; i++) {
    s = next(s)
  }
  return s
}

function next(iterator) {
  return concat(map(g => iter([g.length, g[0]]), group(iterator)));
}

function concat(iterator) {
  let inner = null;
  return {
    next() {
      if (inner === null) {
        let {innerIter, done} = iterator.next();
        if (done) {
          return {done: true};
        }
        inner = innerIter;
      }

      let {value, done} = inner.next();

      if (done) {
        return {done: true};
      } else {
        return {value: value, done: false};
      }
    }
  }

}

// 갯수 , 숫자 묶음으로 만들어줌
function map(func, iterator) {
  return {
    next() {
      let {value, done} = iterator.next();

      if (done) {
        return {done: true};
      } else {
        return {value: func.apply(value), done: false};
      }
    }
  }
}

// 같은 숫자끼리 그룹핑
function group(iterator) {
  let grouping = null;
  return {
    next() {
      while (true) {
        let {value, done} = iterator.next();

        if (done && grouping == null) {
          return {done: true};
        } else if (done) {
          let result = grouping;
          grouping = null;
          return {done: false, value: result};
        } else if (grouping === null) {
          grouping = [value];
        } else if (grouping[0] === value) {
          grouping.push(value);
        } else {
          let result = grouping;
          grouping = [value];
          return {done: false, value: result};
        }
      }
    }
  }
}

function iter(obj) {
  return obj[Symbol.iterator]();
}

function uniter(it) {
  return {
    [Symbol.iterator]: function() {
      return it;
    }
  }
}

function run() {
  for(let a of uniter(ant(10))) {
    process.stdout.write(`${a}`);
  }
}