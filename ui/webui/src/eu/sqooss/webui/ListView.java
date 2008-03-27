/*
 * This file is part of the Alitheia system, developed by the SQO-OSS
 * consortium as part of the IST FP6 SQO-OSS project, number 033331.
 *
 * Copyright 2007-2008-2008 by the SQO-OSS consortium members <info@sqo-oss.eu>
 * Copyright 2007-2008-2008 by Sebastian Kuegler <sebas@kde.org>
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials provided
 *       with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package eu.sqooss.webui;

import java.util.Iterator;
import java.util.ArrayList;
import eu.sqooss.scl.WSSession;
import eu.sqooss.scl.WSException;
import eu.sqooss.scl.result.WSResult;
import eu.sqooss.scl.result.WSResultEntry;

class ListView {

    WSSession session;
    WSResult items = null;
    String error;

    public ListView () {
        // Try to connect
        try {
            session = new WSSession("alitheia", "alitheia", "http://localhost:8088/sqooss/services/ws");
        } catch (WSException wse) {
            //TODO
            wse.printStackTrace();
        } catch (java.util.NoSuchElementException ex) {
            error = "<b>[ERROR] No available project were found!</b>";
            session = null;
        }
    }

    public void setItems (WSResult _items) {
        items = _items;
    }

    public WSResult getItems () {
        return items;
    }


    public String getHtml() {
        if (session == null) {
            return null;
        }
        StringBuilder html = new StringBuilder("<!-- ListView -->\n<ul>");
        if (items == null) {
            return "hhhhhhhhh.";
            //return null;
        } else {
            html.append("<h2>Found " + items.getRowCount() + " projects ...</h2>");
        }
        Iterator <ArrayList<WSResultEntry>> itemlist = items.iterator();
        if (!itemlist.hasNext()) {
            html.append("No project records found.");
        }
        
        while (itemlist.hasNext()) {
            ArrayList <WSResultEntry> p_item = itemlist.next();
            Iterator <WSResultEntry> oneitemlist = p_item.iterator();
            Project nextProject = new Project(); // TODO: Fix this
            html.append("<li>" + nextProject.getHtml() + "</li>");
        }
        html = html.append("\n</ul>\n");
        return html.toString();
    }

    public void retrieveData () {
        return;
    }

}