package org.ircenter.server.web.edit;

/**
 * User: Seledkov Kostyantyn
 * Date: 20.03.12
 * Time: 0:14
 */
public class EditAjaxResponse {


    private boolean complete;

    public EditAjaxResponse(boolean complete) {
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

}
