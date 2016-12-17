package org.ircenter.server.web.book;

import org.ircenter.server.bean.evidence.ProgrammTv;
import org.ircenter.server.bean.evidence.ProgrammTvType;
import org.ircenter.server.bean.evidence.TextEvidence;
import org.ircenter.server.dao.evidence.ProgrammTvDAO;
import org.ircenter.server.dao.evidence.TextEvidenceDAO;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * User: Seledkov Kostyantyn
 * Date: 23.05.12
 * Time: 20:13
 */
@Controller
@RequestMapping("/book")
public class BookController {

    private ProgrammTvDAO programmTvDAO;
    private TextEvidenceDAO textEvidenceDAO;

    @RequestMapping("/{id}")
    public String book(@PathVariable String id, Model model) {
        List<ProgrammTv> programmTvList = programmTvDAO.getProgrammTvByType(ProgrammTvType.CNL);
        Calendar newCal = new GregorianCalendar();

        for (ProgrammTv programmTv : programmTvList) {
            newCal.setTime(programmTv.getDate());
            int day = newCal.get(Calendar.DAY_OF_WEEK);
            switch (day) {
                case 1:     //Воскре
                    programmTv.setDay("Воскресенье");
                    break;
                case 2:             //Понед
                    programmTv.setDay("Понедельник");
                    break;
                case 3:
                    programmTv.setDay("Вторник");
                    break;
                case 4:
                    programmTv.setDay("Среда");
                    break;
                case 5:
                    programmTv.setDay("Четверг");
                    break;
                case 6:
                    programmTv.setDay("Пятница");
                    break;
                case 7:
                    programmTv.setDay("Суббота");
                    break;
            }
        }

        model.addAttribute("programmTvList", programmTvList);


        List<TextEvidence> textEvidenceList = textEvidenceDAO.getTextEvidence(0, 3);

        for (TextEvidence textEvidence : textEvidenceList) {
            if (textEvidence.getTitle().length() > 30) {
                textEvidence.setTitle(textEvidence.getTitle().substring(0, 30) + "...");
            }
            textEvidence.setText(Jsoup.parse(textEvidence.getText()).text());
            if (textEvidence.getText().length() > 40) {
                textEvidence.setText(textEvidence.getText().substring(0, 40) + "...");
            } else {
                textEvidence.setText(Jsoup.parse(textEvidence.getText()).text() + "...");
            }
        }
        model.addAttribute("textEvidenceList", textEvidenceList);

        return "/book/" + id;
    }

    @Autowired
    public void setTextEvidenceDAO(TextEvidenceDAO textEvidenceDAO) {
        this.textEvidenceDAO = textEvidenceDAO;
    }

    @Autowired
    public void setProgrammTvDAO(ProgrammTvDAO programmTvDAO) {
        this.programmTvDAO = programmTvDAO;
    }

}
