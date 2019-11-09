package io.ashimjk.merge;

import io.ashimjk.helper.PayloadHelper;
import lombok.experimental.UtilityClass;
import org.joda.money.Money;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.module.jsr310.Jsr310Module;
import org.modelmapper.module.jsr310.Jsr310ModuleConfig;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
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

        MODEL_MAPPER.createTypeMap(Money.class, Money.class)
                .setConverter(ctx -> Money.parse(ctx.getSource().toString()));

        MODEL_MAPPER.typeMap(List.class, List.class)
                .addMappings(mapper -> mapper.with(provisionRequest -> new ArrayList<>()));
    }

    public static <S, D> D merge(S source, D destination) {

        if (Objects.isNull(destination) || Objects.isNull(source)) {
            return null;
        }

        Object clonedSource = cloneObject(source, source.getClass());

        MODEL_MAPPER.map(destination, clonedSource);
        MODEL_MAPPER.map(clonedSource, destination);

        return destination;
    }

    private static <T> Object cloneObject(T object, Class clazz) {
        String convert = PayloadHelper.convertToString(object);
        return PayloadHelper.toObject(convert, clazz);
    }

}
