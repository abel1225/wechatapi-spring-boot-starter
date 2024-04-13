package me.abel.qywechatapi.encoder.base64;

public interface BinaryEncoder extends Encoder {
    byte[] encode(byte[] var1) throws EncoderException;
}