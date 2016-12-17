package org.ircenter.server.web.biography;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: Seledkov Kostyantyn
 * Date: 26.05.12
 * Time: 14:51
 */
@Controller
@RequestMapping("/biography")
public class BiographyController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String biography(Model model) {
        return "/new/biography";
    }
}
