P0: 
    flag[0] = true;
    victim = 0;
    while (flag[1] == true && victim != r)
    {
        // busy wait
    }
    // critical section
    ...
    // end of critical section
    flag[0] = false;
    
P1: 
    flag[1] = true;
    victim++;
    while (flag[0] == true && victim == r)
    {
        // busy wait
    }
    
    // critical section
    ...
    // end of critical section
    flag[1] = false;