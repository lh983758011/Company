package com.goldenchef.company.message;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.goldenchef.company.R;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.domain.EaseEmojicon;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.widget.EaseChatExtendMenu;
import com.hyphenate.easeui.widget.EaseChatInputMenu;
import com.hyphenate.easeui.widget.EaseVoiceRecorderView;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.chatrow.EaseChatRowImage;
import com.hyphenate.easeui.widget.chatrow.EaseChatRowText;
import com.hyphenate.easeui.widget.chatrow.EaseChatRowVoice;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;

/**
 * Created by luo-hao on 2017/5/17.
 */

public class ChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {

    private static final int MESSAGE_TYPE_RECV_TXT = 1;
    private static final int MESSAGE_TYPE_SENT_TXT = 2;
    private static final int MESSAGE_TYPE_RECV_IMAGE = 3;
    private static final int MESSAGE_TYPE_SENT_IMAGE = 4;
    private static final int MESSAGE_TYPE_RECV_VOICE = 5;
    private static final int MESSAGE_TYPE_SENT_VOICE = 6;

    static final int ITEM_TAKE_PICTURE = 1;
    static final int ITEM_PICTURE = 2;
    static final int ITEM_LOCATION = 3;

    private ChatActivity mChatActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mChatActivity = (ChatActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, null);
    }

    @Override
    protected void initView() {
        super.initView();
        inputMenu.setChatInputMenuListener(new EaseChatInputMenu.ChatInputMenuListener() {
            @Override
            public void onSendMessage(String content) {
                sendTextMessage(content);
            }

            @Override
            public void onBigExpressionClicked(EaseEmojicon emojicon) {
                sendBigExpressionMessage(emojicon.getName(), emojicon.getIdentityCode());
            }

            @Override
            public boolean onPressToSpeakBtnTouch(View v, MotionEvent event) {
                //TODO  需要开启麦克风权限和写入文件权限
                if (mChatActivity.requestRecordAudio() && mChatActivity.requestWriteExternalStorage())
                    return voiceRecorderView.onPressToSpeakBtnTouch(v, event, new EaseVoiceRecorderView.EaseVoiceRecorderCallback() {

                        @Override
                        public void onVoiceRecordComplete(String voiceFilePath, int voiceTimeLength) {
                            sendVoiceMessage(voiceFilePath, voiceTimeLength);
                        }
                    });
                else
                    return false;
            }
        });

        for (int i = 0; i < itemStrings.length; i++) {
            inputMenu.registerExtendMenuItem(itemStrings[i], itemdrawables[i], itemIds[i], new EaseChatExtendMenu.EaseChatExtendMenuItemClickListener() {
                @Override
                public void onClick(int itemId, View view) {
                    switch (itemId) {
                        case ITEM_TAKE_PICTURE:
                            //TODO 需要开启相机权限
                            if (mChatActivity.requestCamera())
                                selectPicFromCamera();
                            break;
                        case ITEM_PICTURE:
                            selectPicFromLocal();
                            break;
                        case ITEM_LOCATION:
//                startActivityForResult(new Intent(getActivity(), EaseBaiduMapActivity.class), REQUEST_CODE_MAP);
                            break;

                        default:
                            break;
                    }
                }
            });
        }
    }

    //设置扩展属性
    @Override
    public void onSetMessageAttributes(EMMessage message) {

    }

    @Override
    public void onEnterToChatDetails() {

    }

    @Override
    public void onAvatarClick(String username) {

    }

    @Override
    public void onAvatarLongClick(String username) {

    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {
        return false;
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {

    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        return false;
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {

        EaseCustomChatRowProvider customChatRowProvider = new EaseCustomChatRowProvider() {
            @Override
            public int getCustomChatRowTypeCount() {
                return 6;
            }

            @Override
            public int getCustomChatRowType(EMMessage message) {
                if (message.getType() == EMMessage.Type.TXT) {
                    return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_TXT : MESSAGE_TYPE_SENT_TXT;
                } else if (message.getType() == EMMessage.Type.IMAGE) {
                    return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_IMAGE : MESSAGE_TYPE_SENT_IMAGE;
                } else if (message.getType() == EMMessage.Type.VOICE) {
                    return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_VOICE : MESSAGE_TYPE_SENT_VOICE;
                }
                return 0;
            }

            @Override
            public EaseChatRow getCustomChatRow(EMMessage message, int position, BaseAdapter adapter) {
                if (message.getType() == EMMessage.Type.TXT)
                    return new EaseChatRowText(getContext(), message, position, adapter);
                else if (message.getType() == EMMessage.Type.IMAGE)
                    return new EaseChatRowImage(getContext(), message, position, adapter);
                else if (message.getType() == EMMessage.Type.VOICE)
                    return new EaseChatRowVoice(getContext(), message, position, adapter);

                return null;
            }
        };

        return customChatRowProvider;
    }
}
