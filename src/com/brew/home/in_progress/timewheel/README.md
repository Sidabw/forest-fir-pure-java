# 时间轮

* 参考链接已经找不到了..
* 之间一直不懂时间轮是怎么转起来的，也就是当前tick是怎么挪到下一个tick的
* 实际就是死循环，一直拿当前时间，curTickFinishTime - curTime，即需要的sleepTime