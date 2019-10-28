package io.ashimjk.genericimpl.support;

import lombok.experimental.UtilityClass;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.modelmapper.module.jsr310.Jsr310ModuleConfig;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Currency;
import java.util.Objects;

@UtilityClass
public class ModelMerger {

    private static final ModelMapper MODEL_MAPPER;

    static {

        MODEL_MAPPER = new ModelMapper();
        MODEL_MAPPER.registerModule(new Jsr310Module(Jsr310ModuleConfig.builder().zoneId(ZoneOffset.UTC).build()));
        MODEL_MAPPER.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(Conditions.isNotNull())
                .setDeepCopyEnabled(true);

        MODEL_MAPPER.createTypeMap(Currency.class, Currency.class)
                .setConverter(ctx -> Currency.getInstance(ctx.getSource().getCurrencyCode()));

        MODEL_MAPPER.createTypeMap(String.class, LocalDateTime.class)
                .setConverter(ctx -> LocalDateTime.parse(ctx.getSource()));
    }

    public static <S, D> D merge(S source, D destination) {

        if (Objects.isNull(destination) || Objects.isNull(source)) {
            return null;
        }

        Object clonedDest = cloneObject(destination, destination.getClass());

        Object clonedSource = cloneObject(source, source.getClass());

        MODEL_MAPPER.map(clonedDest, clonedSource);
        MODEL_MAPPER.map(clonedSource, clonedDest);

        return (D) clonedDest;
    }

    public static <S, D> S mergeStoD(S source, D destination) {

        if (Objects.isNull(destination) || Objects.isNull(source)) {
            return null;
        }

        Object clonedDest = cloneObject(destination, destination.getClass());

        Object clonedSource = cloneObject(source, source.getClass());

        MODEL_MAPPER.map(clonedDest, clonedSource);

        return (S) clonedSource;
    }

    private static <T> Object cloneObject(T object, Class clazz) {
        String convert = PayloadHelper.convertToString(object);
        return PayloadHelper.toObject(convert, clazz);
    }

}
