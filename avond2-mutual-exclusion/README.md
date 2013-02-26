# MUTUAL EXCLUSION

Thread: 

- ordered events
- $$ a0 \\rightarrow a1 \\rightarrow a2 \\rightarrow … $$ 
    - (github-math: ![equation](http://latex.codecogs.com/gif.latex?a0%20%5Crightarrow%20a1%20%5Crightarrow%20a2%20%5Crightarrow%20...))

Thread State + Local Variables
System State = Global Variables + Union (thread states)

Interval: A0 = (a0, a1)

Precedence: A0 -> B0 (end of A0 before start of B0)

-> :: partiële ordening

Critical sections should either

$$ CS_i^k \\rightarrow CS_j^k  \\oplus  CS_j^k \\rightarrow CS_i^k $$

(github-math: ![equation](http://latex.codecogs.com/gif.latex?CS_i%5Ek%20%5Crightarrow%20CS_j%5Ek%20%20%5Coplus%20%20CS_j%5Ek%20%5Crightarrow%20CS_i%5Ek)) 

## 2-THREAD LOCK

### LockOne

Dmv. 2 booleans

    lock() {
        flag[i] = true;
        while (flag[j]) /* wait */;
    }

Fails deadlock freedom if `flag[i] = true;` and `flag[j] = true;` are executed concurrently.

### LockTwo

    lock() {
        victim = i;
        while (victim == i) /* wait */;
    }

Fails deadlock freedom if executed sequentially.

### Peterson's algorithm:

    lock() {
        flag[i] = true;
        victim = i;
        while (flag[j] && victim == i) /* wait */;
        // ^--- wait while other interested & I'm the victim
    }

Deadlock free because both threads cannot be blocked at the same time (one of
them is not the victim).

Also starvation free because others always have precedence.

## N THREADS

Fuck gemist…

### Filter Lock

Peterson's algorithm at many levels.

There can be M multiple threads at a level, but since at least one must be a victim so at most  M-1 can go up another level.

## Exercises

