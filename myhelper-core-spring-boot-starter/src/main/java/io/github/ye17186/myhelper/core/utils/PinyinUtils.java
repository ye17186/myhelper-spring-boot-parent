package io.github.ye17186.myhelper.core.utils;

import io.github.ye17186.myhelper.core.exception.BizException;
import io.github.ye17186.myhelper.core.web.error.ErrorCode;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author yemeng20
 */
public class PinyinUtils {

    private static final HanyuPinyinOutputFormat FORMAT1 = new HanyuPinyinOutputFormat();
    private static final HanyuPinyinOutputFormat FORMAT2 = new HanyuPinyinOutputFormat();
    private static final HanyuPinyinOutputFormat FORMAT3 = new HanyuPinyinOutputFormat();

    static {
        FORMAT1.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        FORMAT2.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
        FORMAT3.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
    }

    /**
     * 汉字转纯汉语拼音，不带声调
     *
     * @param hanZi 汉字
     * @return 拼音
     */
    public static String toPinyin(String hanZi) {

        try {
            return PinyinHelper.toHanYuPinyinString(hanZi, FORMAT1, StringPool.EMPTY, false);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new BizException(ErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * 汉字转汉语拼音，带数字声调
     *
     * @param hanZi 汉字
     * @return 拼音
     */
    public static String toPinyinWithNumberTone(String hanZi) {

        try {
            return PinyinHelper.toHanYuPinyinString(hanZi, FORMAT2, StringPool.EMPTY, false);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new BizException(ErrorCode.SYSTEM_ERROR);
        }
    }

    /**
     * 汉字转汉语拼音，带符合声调
     *
     * @param hanZi 汉字
     * @return 拼音
     */
    public static String toPinyinWithMarkTone(String hanZi) {

        try {
            return PinyinHelper.toHanYuPinyinString(hanZi, FORMAT3, StringPool.EMPTY, false);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new BizException(ErrorCode.SYSTEM_ERROR);
        }
    }
}
