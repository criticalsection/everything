/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise15;

import java.util.concurrent.locks.Lock;

/**
 *
 * @author rix0rrr
 */
public class FastPath {
    private Lock lock;
    private volatile int x = -1, y = -1;
    private volatile boolean locked = false;
    
    public FastPath(Lock lock) {
        this.lock = lock;
    }
    
    public void lock(int i) {
        printState(i, "trylock ");
        x = i;
        printState(i, "wants");
        while (y != -1) { /* wait */ };
        printState(i, "canhave");
        y = i;
        printState(i, "has");
        if (x != i) {
            printState(i, "slowpath");
            lock.lock();
            locked = true;
        } else {
            printState(i, "fastpath");
        }
        printState(i, "locked");
    }
    
    public void unlock(int i) {
        printState(i, "unlock");
        y = -1;
        printState(i, "released");
        if (locked) lock.unlock();
        locked = false;
        printState(i, "unlocked");
    }
    
    private void printState(int i, String where) {
        System.err.println(String.format("%d %-10s x = %2d, y = %2d, locked = %b", i, where, x, y, locked));
        
    }
}
