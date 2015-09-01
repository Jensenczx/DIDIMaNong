package ui;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import com.example.chenjensen.testapp.R;

import fragment.MsgFragment;
import fragment.Testfragment;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;

/**
 * Created by chenjensen on 15/6/6.
 */
public class MyNavigationDrawer extends MaterialNavigationDrawer{
    private Context myContext;
    @Override
    public void init(Bundle bundle) {
        //question about how to recycle bitmap
        myContext = this.getBaseContext();
        initAccount();
        initSection();
    }
    public void initAccount(){
        MaterialAccount account = new MaterialAccount(this.getResources(),"Jensen","精通午夜时分为单身妹子修电脑",R.drawable.ic_photo,R.drawable.bg_account);
        this.addAccount(account);
    }
    public void initSection(){
        MaterialSection homeSection = newSection("首页",R.drawable.ic_shouye,new Testfragment());
        this.addSection(homeSection);
        MaterialSection msgSection = newSection("消息",R.drawable.ic_xiaoxi,new MsgFragment());
        this.addSection(msgSection);
        msgSection.setNotifications(85);
        MaterialSection peopleSection = newSection("联系人",R.drawable.ic_lianxiren,new MsgFragment());
        this.addSection(peopleSection);
        MaterialSection receiverSection = newSection("应邀",R.drawable.ic_jieshou,new MsgFragment());
        this.addSection(receiverSection);
        MaterialSection sendSection = newSection("发送",R.drawable.ic_send,new MsgFragment());
        this.addSection(sendSection);
        MaterialSection settingSection = newSection("设置",R.drawable.ic_shezhi,new MsgFragment());
        this.addSection(settingSection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
