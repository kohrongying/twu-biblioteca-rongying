package com.twu.biblioteca;

public class PromptBuilder {
    private String header;
    private Loanable[] resources;
    private String[] actionMenu;
    private String[] menu;

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder("");
        strBuilder.append(this.buildHeader());
        if (this.menu != null) {
            strBuilder.append(this.buildMenu());
        }

        if (this.resources != null) {
            strBuilder.append(this.buildResources());
        }

        if (this.actionMenu != null) {
            strBuilder.append("\n===== What would you like to do? =====\n");
            strBuilder.append(this.buildActionMenu());
        }

        strBuilder.append("\nYour input: ");

        return strBuilder.toString();
    }

    public PromptBuilder(String header) {
        this.header = header;
    }

    public String buildHeader() {
        return "\n===== " + this.header.toUpperCase() + " =====\n";
    }

    public String[] getMenu() {
        return menu;
    }

    public void setMenu(String[] menu) {
        this.menu = menu;
    }

    public String buildMenu() {
        String menuOptions = "";
        for (String s : this.menu) {
            menuOptions += s + "\n";
        }
        return menuOptions;
    }

    public Loanable[] getResources() {
        return resources;
    }

    public void setResources(Loanable[] resources) {
        this.resources = resources;
    }

    public String buildResources() {
        String bookList = "";
        if (this.resources.length > 0) {
            for (int i = 0; i < this.resources.length; i += 1) {
                bookList += (i + 1)
                        + " - "
                        + this.resources[i].toString()
                        + "\n";
            }
        } else {
            bookList += "There are none at the moment\n";
        }
        return bookList;
    }

    public String[] getActionMenu() {
        return this.actionMenu;
    }

    public void setActionMenu(String[] actionMenu) {
        this.actionMenu = actionMenu;
    }

    public String buildActionMenu() {
        String actionOptions = "";
        for (String s : this.actionMenu) {
            actionOptions += s + "\n";
        }
        return actionOptions;
    }
}
