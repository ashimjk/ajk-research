package io.ashimjk.number_to_words.contact.domain;

import io.ashimjk.number_to_words.contact.Language;
import io.ashimjk.number_to_words.contact.Translator;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class EnglishTranslator implements Translator {
    public static final String[][] ENGLISH_CURRENCY_DETAILS =
        new String[][]{
            {"KPW", "North korean", "Won", "Chon"},
            {"KRW", "South korean", "Won", "Jeon"},
            {"AED", "United arab emirates", "Dirham", "Fils"},
            {"AFN", "Afghan", "Afghani", "Pul"},
            {"ALL", "Albanian", "Lek", "Qindarkë"},
            {"AMD", "Armenian", "Dram", "Luma"},
            {"ANG", "Netherlands antillean", "Guilder", "Cent"},
            {"AOA", "Angolan", "Kwanza", "Cêntimo"},
            {"ARS", "Argentine", "Peso", "Centavo"},
            {"AUD", "Australian", "Dollar", "Cent"},
            {"AWG", "Aruban", "Florin", "Cent"},
            {"AZN", "Azerbaijani", "Manat", "Qəpik"},
            {"BAM", "Bosnia and herzegovina", "convertible Mark", "Fening"},
            {"BBD", "Barbadian", "Dollar", "Cent"},
            {"BDT", "Bangladeshi", "Taka", "Poisha"},
            {"BGN", "Bulgarian", "Lev", "Stotinka"},
            {"BHD", "Bahraini", "Dinar", "Fils"},
            {"BIF", "Burundian", "Franc", "Centime"},
            {"BMD", "Bermudian", "Dollar", "Cent"},
            {"BND", "Brunei", "Dollar", "Sen"},
            {"BOB", "Bolivian", "Boliviano", "Centavo"},
            {"BRL", "Brazilian", "Real", "Centavo"},
            {"BSD", "The bahamian", "Dollar", "Cent"},
            {"BTN", "Bhutanese", "Ngultrum", "Chetrum"},
            {"BWP", "Botswana", "Pula", "Thebe"},
            {"BYN", "Belarusian", "Ruble", "Kapyeyka"},
            {"BZD", "Belize", "Dollar", "Cent"},
            {"CAD", "Canadian", "Dollar", "Cent"},
            {"CHF", "Swiss", "Franc", "Rappen"},
            {"CLP", "Chilean", "Peso", "Centavo"},
            {"CNY", "Chinese", "Yuan", "Fen"},
            {"COP", "Colombian", "Peso", "Centavo"},
            {"CRC", "Costa rican", "Colón", "Céntimo"},
            {"CUP", "Cuban", "Peso", "Centavo"},
            {"CVE", "Cape verdean", "Escudo", "Centavo"},
            {"CZK", "Czech", "Koruna", "Haléř"},
            {"GMD", "Gambian", "Dalasi", "Butut"},
            {"DJF", "Djiboutian", "Franc", "Centime"},
            {"DKK", "Danish", "Krone", "Øre"},
            {"DOP", "Dominican", "Peso", "Centavo"},
            {"DZD", "Algerian", "Dinar", "Santeem"},
            {"EGP", "Egyptian", "Pound", "Piastre"},
            {"ERN", "Eritrean", "Nakfa", "Cent"},
            {"ETB", "Ethiopian", "Birr", "Santim"},
            {"EUR", "European union", "Euro", "Cent"},
            {"FJD", "Fijian", "Dollar", "Cent"},
            {"FKP", "Falkland islands", "Pound", "Penny"},
            {"CDF", "Congolese", "Franc", "Centimes"},
            {"XAF", "Central african", "Franc", "Centimes"},
            {"GBP", "British", "Pound", "Penny"},
            {"GEL", "Georgia", "Lari", "Tetri"},
            {"GHS", "Ghanaian", "Cedi", "Pesewa"},
            {"GIP", "Gibraltar", "Pound", "Penny"},
            {"GNF", "Guinean", "Franc", "Centime"},
            {"GTQ", "Guatemalan", "Quetzal", "Centavo"},
            {"GYD", "Guyanese", "Dollar", "Cent"},
            {"HKD", "Hong kong", "Dollar", "Cent"},
            {"HNL", "Honduran", "Lempira", "Centavo"},
            {"HRK", "Croatian", "Kuna", "Lipa"},
            {"HTG", "Haitian", "Gourde", "Centime"},
            {"HUF", "Hungarian", "Forint", "Fillér"},
            {"IDR", "Indonesian", "Rupiah", "Sen"},
            {"ILS", "Israeli new", "Shekel", "Agora"},
            {"INR", "Indian", "Rupee", "Paisa"},
            {"IQD", "Iraqi", "Dinar", "Fils"},
            {"IRR", "Iranian", "Rial", "Dinar"},
            {"ISK", "Icelandic", "Króna", "Eyrir"},
            {"JMD", "Jamaican", "Dollar", "Cent"},
            {"JOD", "Jordanian", "Dinar", "Fils"},
            {"JPY", "Japanese", "Yen", "Sen"},
            {"KES", "Kenyan", "Shilling", "Cent"},
            {"KGS", "Kyrgyzstani", "Som", "Tyiyn"},
            {"KHR", "Cambodian", "Riel", "Sen"},
            {"KMF", "Comorian", "Franc", "Centime"},
            {"KWD", "Kuwaiti", "Dinar", "Fils"},
            {"KYD", "Cayman islands", "Dollar", "Cent"},
            {"KZT", "Kazakhstani", "Tenge", "Tïin"},
            {"LAK", "Lao", "Kip", "Att"},
            {"LBP", "Lebanese", "Pound", "Piastre"},
            {"LKR", "Sri lankan", "Rupee", "Cent"},
            {"LRD", "Liberian", "Dollar", "Cent"},
            {"LSL", "Lesotho", "Loti", "Sente"},
            {"LYD", "Libyan", "Dinar", "Dirham"},
            {"MAD", "Moroccan", "Dirham", "Centime"},
            {"MDL", "Moldovan", "Leu", "Ban"},
            {"MGA", "Malagasy", "Ariary", "Iraimbilanja"},
            {"MMK", "Burmese", "Kyat", "Pya"},
            {"MNT", "Mongolia", "Tetri", "Möngö"},
            {"MOP", "Macanese", "Pataca", "Avo"},
            {"MRO", "Mauritanian", "Ouguiya", "Khoums"},
            {"MUR", "Mauritian", "Rupee", "Cent"},
            {"MVR", "Maldivian", "Rufiyaa", "Laari"},
            {"MWK", "Malawian", "Kwacha", "Tambala"},
            {"MXN", "Mexican", "Peso", "Centavo"},
            {"MYR", "Malaysian", "Ringgit", "Sen"},
            {"MZN", "Mozambican", "Metical", "Centavo"},
            {"NAD", "Namibian", "Dollar", "Cent"},
            {"NGN", "Nigerian", "Naira", "Kobo"},
            {"NIO", "Nicaraguan", "Córdoba", "Centavo"},
            {"NOK", "Norwegian", "Krone", "Øre"},
            {"NPR", "Nepalese", "Rupee", "Deni"},
            {"NZD", "New zealand", "Dollar", "Cent"},
            {"OMR", "Omani", "Rial", "Baisa"},
            {"PAB", "Panamanian", "Balboa", "Centésimo"},
            {"PEN", "Nuevo", "Sol", "Céntimo"},
            {"PGK", "Papua new guinean", "Kina", "Toea"},
            {"PHP", "Philippine", "Peso", "Sentimo"},
            {"PKR", "Pakistan", "Rupee", "Paisa"},
            {"PLN", "Polish", "Złoty", "Grosz"},
            {"PYG", "Paraguayan", "Guaraní", "Céntimo"},
            {"QAR", "Qatari", "Riyal", "Dirham"},
            {"RON", "Romanian", "Leu", "Ban"},
            {"RSD", "Serbian", "Dinar", "Para"},
            {"RUB", "Russian", "Ruble", "Kopek"},
            {"RWF", "Rwandan", "Franc", "Centime"},
            {"SAR", "Saudi", "Riyal", "Halala"},
            {"SBD", "Solomon islands", "Dollar", "Cent"},
            {"SCR", "Seychellois", "Rupee", "Cent"},
            {"SDG", "Sudanese", "Pound", "Piastre"},
            {"SEK", "Swedish", "Krona", "Öre"},
            {"SGD", "Singapore", "Dollar", "Cent"},
            {"SHP", "Saint helena", "Pound", "Penny"},
            {"SLL", "Sierra leonean", "Leone", "Cent"},
            {"SOS", "Somali", "Shilling", "Cent"},
            {"SRD", "Surinamese", "Dollar", "Cent"},
            {"SSP", "South sudanese", "Pound", "Piastre"},
            {"STD", "São tomé and príncipe", "Dobra", "Cêntimo"},
            {"SVC", "Salvadoran", "Colon", "Centavo"},
            {"SYP", "Syrian", "Pound", "Piastre"},
            {"SZL", "Swazi", "Lilangeni", "Cent"},
            {"THB", "Thai", "Baht", "Satang"},
            {"TJS", "Tajikistani", "Somoni", "Diram"},
            {"TMT", "Turkmenistan", "Manat", "Tennesi"},
            {"TND", "Tunisian", "Dinar", "Millime"},
            {"TOP", "Tongan", "Paʻanga", "Seniti"},
            {"TRY", "Turkish", "Lira", "Kuruş"},
            {"TTD", "Trinidadian", "Dollar", "Cent"},
            {"TWD", "New taiwan", "Dollar", "Cent"},
            {"TZS", "Tanzanian", "Shilling", "Cent"},
            {"UAH", "Ukrainian", "Hryvnia", "Kopiyka"},
            {"UGX", "Ugandan", "Shilling", "Cent"},
            {"USD", "US", "Dollar", "Cent"},
            {"UYU", "Uruguayan", "Peso", "Centésimo"},
            {"UZS", "Uzbekistani", "Soʻm", "Tiyin"},
            {"VEF", "Venezuelan", "Bolívar", "Céntimo"},
            {"VND", "Vietnamese", "Đồng", "Hào"},
            {"VUV", "Vanuatu", "Vatu", "None"},
            {"WST", "Samoan", "Tālā", "Sene"},
            {"XAF", "Central african", "Franc", "Centime"},
            {"XCD", "Eastern caribbean", "Dollar", "Cent"},
            {"XOF", "West african", "Franc", "Centime"},
            {"XPF", "New caledonia", "cfp Franc", "Centime"},
            {"YER", "Yemeni", "Rial", "Fils"},
            {"ZAR", "South african", "Rand", "Cent"},
            {"ZMW", "Zambian", "Kwacha", "Ngwee"},
            {"MKD", "Macedonian", "Denar", "Deni"}
        };
    private static final int INITIAL_CAPACITY = 27;
    private static final String EMPTY_STRING = "";
    private static final String ONE_SPACE = " ";
    private static final String AND = " and ";
    private static final String HUNDRED = "Hundred ";
    private static final String[] FAMILIES;
    private static final ThreadLocal<Deque<Long>> numbers;

    static {
        String[][] var0 = ENGLISH_CURRENCY_DETAILS;
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2) {
            String[] currencyDetails = var0[var2];
            Map<String, String> map = new HashMap();
            map.put("displayName", currencyDetails[1]);
            map.put("unit", currencyDetails[2]);
            map.put("subUnit", currencyDetails[3]);
            Currency.put(currencyDetails[0], Language.ENGLISH, map);
        }

        FAMILIES = new String[]{"", "Thousand", "Million", "Billion", "Trillion"};
        numbers = ThreadLocal.withInitial(LinkedList::new);
    }

    private Map<Integer, String> translation = new HashMap(27);

    public EnglishTranslator() {
        this.translation.put(0, "Zero");
        this.translation.put(1, "One");
        this.translation.put(2, "Two");
        this.translation.put(3, "Three");
        this.translation.put(4, "Four");
        this.translation.put(5, "Five");
        this.translation.put(6, "Six");
        this.translation.put(7, "Seven");
        this.translation.put(8, "Eight");
        this.translation.put(9, "Nine");
        this.translation.put(10, "Ten");
        this.translation.put(11, "Eleven");
        this.translation.put(12, "Twelve");
        this.translation.put(13, "Thirteen");
        this.translation.put(14, "Fourteen");
        this.translation.put(15, "Fifteen");
        this.translation.put(16, "Sixteen");
        this.translation.put(17, "Seventeen");
        this.translation.put(18, "Eighteen");
        this.translation.put(19, "Nineteen");
        this.translation.put(20, "Twenty");
        this.translation.put(30, "Thirty");
        this.translation.put(40, "Forty");
        this.translation.put(50, "Fifty");
        this.translation.put(60, "Sixty");
        this.translation.put(70, "Seventy");
        this.translation.put(80, "Eighty");
        this.translation.put(90, "Ninety");
    }

    public String translate(Long number, Map<String, String> currency, OperationType operationType) {
        for (Long tmp = number; tmp > 0L; tmp = tmp / 1000L) {
            numbers.get().push(tmp % 1000L);
        }

        StringBuilder result = new StringBuilder();

        while (!numbers.get().isEmpty()) {
            Long pop = (Long) ((Deque) numbers.get()).pop();
            result.append(this.translateNumberPart(pop));
        }

        return this.appendCurrency(operationType, currency, result.toString());
    }

    private String appendCurrency(
        OperationType operationType, Map<String, String> currency, String result) {
        StringBuilder finalResult = new StringBuilder(result);
        if (result.isEmpty() || result.charAt(result.length() - 1) != ' ') {
            finalResult.append(" ");
        }

        return operationType == OperationType.INTEGER
            ? finalResult.toString()
            + currency.get("displayName")
            + " "
            + currency.get("unit")
            : finalResult.toString() + currency.get("subUnit");
    }

    private String translateNumberPart(Long pop) {
        return this.process(pop) + this.getFamiliesPart(pop) + this.getDelimiterPart();
    }

    private String getDelimiterPart() {
        return !numbers.get().isEmpty() && (Long) ((Deque) numbers.get()).peek() != 0L
            ? ", "
            : "";
    }

    private String getFamiliesPart(Long pop) {
        return !numbers.get().isEmpty() && pop != 0L
            ? FAMILIES[numbers.get().size()]
            : "";
    }

    private String process(Long pop) {
        String result = "";
        int log10 = (int) Math.log10((double) pop);
        if (log10 == 0) {
            result = result + this.translation.get(pop.intValue()) + " ";
        }

        if (log10 == 1) {
            if (this.translation.containsKey(pop.intValue())) {
                result = result + this.translation.get(pop.intValue()) + " ";
            } else {
                result =
                    result
                        + this.translation.get(pop.intValue() / 10 * 10)
                        + " "
                        + this.translation.get(pop.intValue() % 10)
                        + " ";
            }
        }

        if (log10 == 2) {
            result =
                result
                    + this.translation.get(pop.intValue() / 100)
                    + " "
                    + "Hundred "
                    + this.process(pop % 100L);
        }

        return result;
    }

    public String getDelimiter() {
        return " and ";
    }

    public String translateZero(Map<String, String> currency) {
        return this.appendCurrency(OperationType.INTEGER, currency, "Zero ");
    }
}
