package com.zbkblog.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class IDWorker  implements IdentifierGenerator {
    
    protected static final Logger LOG = LoggerFactory.getLogger(IDWorker.class);

    private static AtomicInteger id = new AtomicInteger(0);
    private static String workId = "0";
    private static long YEARMILLS = 88128000000l;

    public static long generate() {
        Integer wid;
        if(workId == null || workId.trim().length() == 0){// 需要再加上是否数值类型，以及是否越界的验证
            wid = new Random(2).nextInt();
        }else{
            wid = Integer.valueOf(workId);
        }
        return (wid & 0xFFFF) << 48 | ((System.currentTimeMillis()-YEARMILLS) / 1000L & 0xFFFFFFFF) << 16 | id.addAndGet(1) & 0xFFFF;
    }

//    private static AtomicInteger IntId = new AtomicInteger(0);
//
//    public static int getIntId() {
//        return ((int) (System.currentTimeMillis() - 1000000000000L) / 1000) << 16L | (IntId.addAndGet(1));
//    }

//    private long workerId;
//    private long datacenterId;
//    private long sequence = 0L;
//
//    private long twepoch = 1288834974657L;
//
////    private long workerIdBits = 5L;
////    private long datacenterIdBits = 5L;
////    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
////    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
////    private long sequenceBits = 12L;
//
//    private long workerIdBits = 2L;
//    private long datacenterIdBits = 1L;
//    private long maxWorkerId = -1L ^ (-1L << workerIdBits);
//    private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
//    private long sequenceBits = 12L;
//
//    private long workerIdShift = sequenceBits;
//    private long datacenterIdShift = sequenceBits + workerIdBits;
//    private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
//    private long sequenceMask = -1L ^ (-1L << sequenceBits);
//
//    private long lastTimestamp = -1L;
//
//    public IDWorker(){
//        String wId = AppProperties.get("workerId");
//        String dId = AppProperties.get("datacenterId");
//        this.workerId = Long.parseLong(wId);
//        this.datacenterId = Long.parseLong(dId);
//        LOG.info(String.format("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d", timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId));
//
//    }
//
//    public IDWorker(long workerId, long datacenterId) {
//        // sanity check for workerId
//        if (workerId > maxWorkerId || workerId < 0) {
//            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
//        }
//        if (datacenterId > maxDatacenterId || datacenterId < 0) {
//            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
//        }
//        this.workerId = workerId;
//        this.datacenterId = datacenterId;
//        LOG.info(String.format("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d", timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId));
//    }
//
//    public synchronized long nextId() {
//        long timestamp = timeGen();
//
//        if (timestamp < lastTimestamp) {
//            LOG.error(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
//            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
//        }
//
//        if (lastTimestamp == timestamp) {
//            sequence = (sequence + 1) & sequenceMask;
//            if (sequence == 0) {
//                timestamp = tilNextMillis(lastTimestamp);
//            }
//        } else {
//            sequence = 0L;
//        }
//
//        lastTimestamp = timestamp;
//
//        return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
//    }
//
//    protected long tilNextMillis(long lastTimestamp) {
//        long timestamp = timeGen();
//        while (timestamp <= lastTimestamp) {
//            timestamp = timeGen();
//        }
//        return timestamp;
//    }
//
//    protected long timeGen() {
//        return System.currentTimeMillis();
//    }
//
//    public static long generate() {
//        return (new IDWorker()).nextId();
//    }
//
    public static void main(String[] args) {

        for(int i = 0; i< 3; i++){
            System.out.println(IDWorker.generate());
        }

    }

    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        return IDWorker.generate();
    }
}