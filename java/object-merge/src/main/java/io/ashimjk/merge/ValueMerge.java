package io.ashimjk.merge;

import io.ashimjk.helper.PayloadHelper;
import lombok.experimental.UtilityClass;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.modelmapper.module.jsr310.Jsr310ModuleConfig;

import java.time.ZoneOffset;
import java.util.Objects;

@UtilityClass
public class ValueMerge {

    public static <T extends WorkflowEntity> T merge(T current, T latest, Class clazz) {

        if (Objects.isNull(latest) && Objects.isNull(current)) {
            return null;
        }
        if (Objects.isNull(current)) {
            return latest;
        }
        if (Objects.isNull(latest)) {
            return current;
        }
        Jsr310ModuleConfig config = Jsr310ModuleConfig.builder()
            .dateTimePattern("yyyy-MM-dd HH:mm:ss") // default is yyyy-MM-dd HH:mm:ss
            .datePattern("yyyy-MM-dd") // default is yyyy-MM-dd
            .zoneId(ZoneOffset.UTC) // default is ZoneId.systemDefault()
            .build();

        ModelMapper modelMapper = new ModelMapper();
        Jsr310Module module = new Jsr310Module(config);
        module.setupModule(modelMapper);
        modelMapper.registerModule(module);

        Configuration configuration = modelMapper.getConfiguration();

        configuration
            .setPropertyCondition(Conditions.isNotNull())
            .setDeepCopyEnabled(true)
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
            .setAmbiguityIgnored(true);

        Object object = cloneObject(current, clazz);
        modelMapper.map(latest, object);
        return (T) object;
    }

    private static <T> Object cloneObject(T object, Class clazz) {
        String convert = PayloadHelper.convertToString(object);
        return PayloadHelper.toObject(convert, clazz);
    }

}
