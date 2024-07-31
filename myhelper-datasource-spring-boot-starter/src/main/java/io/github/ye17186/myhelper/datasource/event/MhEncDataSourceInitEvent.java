package io.github.ye17186.myhelper.datasource.event;

import com.baomidou.dynamic.datasource.creator.DataSourceProperty;
import com.baomidou.dynamic.datasource.event.DataSourceInitEvent;
import io.github.ye17186.myhelper.core.utils.StringUtils;
import io.github.ye17186.myhelper.core.utils.crypto.CryptoUtils;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ye17186
 * @date 2024/5/30
 */
@Slf4j
public class MhEncDataSourceInitEvent implements DataSourceInitEvent {

    private static final Pattern ENC_PATTERN = Pattern.compile("^ENC\\((.*)\\)$");

    public void beforeCreate(DataSourceProperty dataSourceProperty) {
        String publicKey = dataSourceProperty.getPublicKey();
        if (StringUtils.isNotEmpty(publicKey)) {
            dataSourceProperty.setUsername(this.decrypt(publicKey, dataSourceProperty.getUsername()));
            dataSourceProperty.setPassword(this.decrypt(publicKey, dataSourceProperty.getPassword()));
        }
    }

    public void afterCreate(DataSource dataSource) {
    }

    private String decrypt(String publicKey, String cipherText) {
        if (StringUtils.isNotEmpty(cipherText)) {
            Matcher matcher = ENC_PATTERN.matcher(cipherText);
            if (matcher.find()) {
                try {
                    return CryptoUtils.RSA.decrypt(publicKey, matcher.group(1));
                } catch (Exception ex) {
                    log.error("DynamicDataSourceProperties.decrypt error ", ex);
                }
            }
        }
        return cipherText;
    }
}
