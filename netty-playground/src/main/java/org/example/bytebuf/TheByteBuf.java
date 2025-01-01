package org.example.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class TheByteBuf {
    public static void main(String[] args) {
        readAndWrite();
    }

    private static void readAndWrite() {
        ByteBuf buffer = Unpooled.buffer(10);
        System.out.println("buffer.writableBytes() = " + buffer.writableBytes()); // 10
        System.out.println("buffer.readableBytes() = " + buffer.readableBytes()); // 0

        buffer.writeByte(1);
        buffer.writeByte(2);
        buffer.writeByte(3);
        System.out.println("buffer.writableBytes() = " + buffer.writableBytes()); // 7
        System.out.println("buffer.readableBytes() = " + buffer.readableBytes()); // 3

        byte one = buffer.readByte();
        byte two = buffer.readByte();
        byte three = buffer.readByte();
        System.out.println("one, two, three = " + one + " " + two + " " + three);
        System.out.println("buffer.writableBytes() = " + buffer.writableBytes()); // 7
        System.out.println("buffer.readableBytes() = " + buffer.readableBytes()); // 0

        buffer.discardReadBytes(); // expensive because of coping memory into backwards.
        System.out.println("buffer.writableBytes() = " + buffer.writableBytes()); // 10
        System.out.println("buffer.readableBytes() = " + buffer.readableBytes()); // 0
    }
}
