package me.abel.qywechatapi.encoder.base64;

public interface BinaryDecoder extends Decoder {
    byte[] decode(byte[] var1) throws DecoderException;
}