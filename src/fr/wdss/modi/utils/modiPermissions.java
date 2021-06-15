package fr.wdss.modi.utils;

import org.bukkit.permissions.Permission;

public class modiPermissions {

    public Permission staff = new Permission("modi.staff");
    public Permission admin = new Permission("modi.admin");
    public Permission chat = new Permission("modi.chat");
    //Permission for mute
    public Permission mute = new Permission("modi.chat.mute");
    //Permission for freeze
    public Permission freeze = new Permission("modi.freeze");
    //Permission for maintenance
    public Permission maintenance = new Permission("modi.maintenance");
    public Permission maintenanceEnter = new Permission("modi.maintenance.enter");
    //Permission for gm
    public Permission gm = new Permission("modi.gm");
    //permission for tablist
    public Permission tablist = new Permission("modi.tablist");

}
