package com.perm.kate.api;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Message {
    public String date;
    public long uid;
    //TODO make long
    public String mid;
    public String title;
    public String body;
    //TODO make boolean
    public String read_state;
    public boolean is_out;
    public ArrayList<Attachment> attachments=new ArrayList<Attachment>();
    public Long chat_id;

    public static Message parse(JSONObject o, boolean from_history, long history_uid, boolean from_chat, long me) throws NumberFormatException, JSONException{
        Message m = new Message();
        if(from_chat){
            long from_id=o.getLong("from_id");
            m.uid = from_id;
            m.is_out=(from_id==me);
        }else if(from_history){
            m.uid=history_uid;
            Long from_id = o.getLong("from_id");
            m.is_out=!(from_id==history_uid);
        }else{
            m.uid = o.getLong("uid");
            m.is_out = o.getInt("out")==1;
        }
        m.mid = o.getString("mid");
        m.date = o.getString("date");
        if(!from_history && !from_chat)
            m.title = Api.unescape(o.getString("title"));
        m.body = Api.unescape(o.getString("body"));
        m.read_state = o.getString("read_state");
        if(o.has("chat_id"))
            m.chat_id=o.getLong("chat_id");

        JSONArray attachments=o.optJSONArray("attachments");
        JSONObject geo_json=o.optJSONObject("geo");
        m.attachments=Attachment.parseAttachments(attachments, 0, 0, geo_json);
        return m;
    }

    public static int UNREAD = 1;	 	//��������� �� ��������� 
    public static int OUTBOX = 2;	 	//��������� ��������� 
    public static int REPLIED = 4;	 	//�� ��������� ��� ������ ����� 
    public static int IMPORTANT = 8; 	//���������� ��������� 
    public static int CHAT = 16;    	//��������� ���������� ����� ������
    public static int FRIENDS = 32;		//��������� ���������� ������ 
    public static int SPAM = 64;		//��������� �������� ��� "����"
    public static int DELETED = 128;	//��������� ������� (� �������)
    public static int FIXED = 256; 		//��������� ��������� ������������� �� ���� 
    public static int MEDIA = 512;		//��������� �������� ������������
    public static int BESEDA = 8192;    //������

    public static Message parse(JSONArray a) throws JSONException {
        Message m = new Message();
        m.mid = a.getString(1);
        m.uid = a.getLong(3);
        m.date = a.getString(4);
        m.title = Api.unescape(a.getString(5));
        m.body = Api.unescape(a.getString(6));
        int flag = a.getInt(2);
        m.read_state = ((flag & UNREAD) != 0)?"0":"1";
        m.is_out = (flag & OUTBOX) != 0;
        if ((flag & BESEDA) != 0) {
            m.chat_id = a.getLong(3) & 63;//cut 6 last digits
            JSONObject o= a.getJSONObject(7);
            m.uid = o.getLong("from");
        }
        //m.attachment = a.getJSONArray(7); TODO
        return m;
    }
}
