package io.github.ye17186.myhelper.core.utils.audio;

import org.apache.commons.lang3.ArrayUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/**
 * @author yemeng20
 */
public class WavUtils {

    /**
     * 构建Wav文件
     *
     * @param dataBytes 纯数据字节数组
     * @return Wav文件字节数组
     */
    public static byte[] buildWavFile(byte[] dataBytes) {

        byte[] header = buildWavHeader(dataBytes.length, 8000, 1, 16);
        return ArrayUtils.addAll(header, dataBytes);
    }

    /**
     * 构建Wav文件头
     *
     * @param dataLength    数据长度
     * @param sr            采样率
     * @param channels      声道数
     * @param bitsPerSample 每个采样的比特数
     * @return wav文件头
     */
    private static byte[] buildWavHeader(int dataLength, int sr, int channels, int bitsPerSample) {

        ByteBuffer buffer = ByteBuffer.allocate(44).order(ByteOrder.LITTLE_ENDIAN);
        buffer.put("RIFF".getBytes(StandardCharsets.UTF_8)); // 0-3: RIFF标记
        buffer.putInt(dataLength + 44 - 8); // 4-7: 原始数据长度（不包含RIFF和本字段共8个字节)
        buffer.put("WAVE".getBytes(StandardCharsets.UTF_8)); // 8-11: WAVE标记
        buffer.put("fmt ".getBytes(StandardCharsets.UTF_8)); // 12-15: fmt标记, 4位，空格勿删
        buffer.putInt(16); // 16-19: "fmt"字段的长度
        buffer.putShort((short) 1); // 20-21: 存储音频文件的编码格式，PCM其存储值为1
        buffer.putShort((short) 1); // 22-23: 通道数，单通道(Mono)值为1，双通道(Stereo)值为2
        buffer.putInt(sr); // 24-27: 采样率
        buffer.putInt((short) (sr * channels * bitsPerSample / 8)); // 28-31: 音频数据传送速率
        buffer.putShort((short) (channels * bitsPerSample / 8)); // 32-33: 块对齐/帧大小
        buffer.putShort((short) bitsPerSample); // 34-35: pcm数据位数，一般为8,16,32等
        buffer.put("data".getBytes(StandardCharsets.UTF_8)); // 36-39: data标记
        buffer.putInt(dataLength); // 40-43: data数据长度
        return buffer.array();
    }
}
