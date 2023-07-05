function ant(n) {
  let s = gen([1]);
  for (let i = 0; i < n; i++) {
    s = next(s);
  }
  return s;
}

function next(ns) {
  return concat(map(g => {gen([g.length, g[0]])}, group(ns)));
}

function* concat(gs) {
  for (let g of gs) {
    yield* g;
  }
}

function* map(func, gs) {
  for (const value of gs) {
    yield func.apply(value);
  }
}

function* group(ns) {
  let prev = [ns.next().value];
  for (let value of ns) {
    if (prev[0] !== value) {
      yield prev;
      prev = [value];
    } else {
      prev = [...prev, value];
    }
  }
  yield prev;
}

function* gen(obj) {
  yield* obj;
}