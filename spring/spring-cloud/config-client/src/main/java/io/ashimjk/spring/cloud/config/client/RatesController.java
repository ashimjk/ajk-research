package io.ashimjk.spring.cloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RatesController {

    @Value("${rate}")
    private String rate;

    @Value("${lanecount}")
    private String laneCount;

    @Value("${tollstart}")
    private String tollStart;

    @Value("${environment}")
    private String environment;

    @RequestMapping("/rate")
    public String getRates(Model model) {
        model.addAttribute("rateAmount", rate);
        model.addAttribute("lanes", laneCount);
        model.addAttribute("tollStart", tollStart);
        model.addAttribute("environment", environment);

        return "rateView";
    }

}
