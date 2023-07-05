int next(proc *s) {
    int prev;
    switch (s->ptr) {
        case 0:
            s->ptr = 1;
            return -1;
        case 1: // 0,1이 처음시작
            s->prev = s->next;
            s->count = 1;
            s->ptr = 2;
            return -1;
        case 2: // begin of loop
            if (s->next == 0) {
                s->ptr = 5; // 종료
                return s->count; // A, 갯수 출력
            } else if (s->prev == s->next) {
                s->count++;
                return -1;
            } else {
                s->ptr = 3;
                return s->count; // 1, 갯수 출력
            }
        case 3: // 이전 숫자와 현재 숫자가 다름 (count 초기화)
            prev = s->prev;
            s->prev = s->next;
            s->count = 1;
            s->ptr = 4;
            return prev; // 2, 숫자 출력
        case 4:
            s->ptr = 2;
            return -1; // end of loop
        case 5:
            s->ptr = 6;
            return s->prev; // B, 숫자 출력
        case 6:
            return 0; // exit coroutine
        default:
            assert("Unreachable");
            return 0;
    }
}

// prepare coroutines
proc* procs = (proc*)calloc(n + 1, sizeof(proc));
procs[0].proc = &init;
for (int i = 1; i < n + 1; i++) {
    procs[i].proc = &next;
}

// dispatch loop
int cur = n;
while (cur < n + 1) {
    int result = procs[cur].proc(&procs[cur]);
    if (result == -1) {
        cur--;
    } else if (cur < n) {
        cur++;
        procs[cur].next = result;
    } else if (result != 0) {
        printf("%d", result);
    } else {
        printf("\n");
        break;
    }
}