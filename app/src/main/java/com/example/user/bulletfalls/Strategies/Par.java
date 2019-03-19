package com.example.user.bulletfalls.Strategies;


public class Par<T,J> {
    T t;
    J j;

    private Par()
    {

    }
    public Par(T left, J right) {
        this.t=left;
        this.j=right;
    }

    public void Par(T t,J j)
    {
        this.t=t;
        this.j=j;
    }
    public T getLeft()
    {
        return this.t;
    }

    public J getRight()
    {
        return this.j;
    }

    public void setLeft(T t)
    {
        this.t=t;
    }
    public void setRight(J j)
    {
        this.j=j;
    }
}
