<script setup>
import Typography from '../../components/Typography.vue';
import Sender from './Sender.vue';
import Receiver from './Receiver.vue';
import { nextTick, onBeforeMount, onBeforeUnmount, onUnmounted, ref, watch } from 'vue';
import { getSimpleProfile } from '../../api/userProfile.js';
import { useRoute, useRouter } from 'vue-router';
import { useUserStore } from '../../store/userStore.js';
import { useToast } from 'vue-toastification';
import { getUnreadMessage } from '../../api/chat.js';

const route = useRoute();
const router = useRouter();
const chats = ref(null);
const userStore = useUserStore();
const content = ref('');
const toast = useToast();
const chatBox = ref(null);
let ws = null;
const send = () => {
    if (content.value === '') {
        toast.warning('发送信息为空');
    } else {
        const obj = {
            sender: userStore.userProfile.id,
            receiver: route.params.id,
            content: content.value
        };
        content.value = '';
        ws.send(JSON.stringify(obj));
    }
};
const receiver = ref({
    nickname: '',
    avatar: '',
    id: 0
});

onBeforeMount(async () => {
    const { data } = await getSimpleProfile(route.params.id);
    receiver.value = data;
});
const connect = () => {
    const ws = new WebSocket(`ws://localhost:8080/aoi/chat/sender/${userStore.userProfile.id}/receiver/${route.params.id}`);
    ws.onopen = async () => {
        const { data } = await getUnreadMessage(route.params.id);
        chats.value.push(...data);
        await nextTick(() => {
            chatBox.value.scrollTo({
                top: chatBox.value.scrollHeight
            });
        });
    };
    ws.onmessage = (event) => {
        const message = JSON.parse(event.data);
        chats.value.push(message);
        nextTick(() => {
            chatBox.value.scrollTo({
                top: chatBox.value.scrollHeight
            });
        });
        localStorage.setItem('chat:' + userStore.userProfile.id + '-' + receiver.value.id, JSON.stringify(chats.value));
    };
    return ws;
};
onBeforeUnmount(() => {
    ws.close();
});
watch(() => userStore.signed, (value) => {
    if (value) {
        chats.value = JSON.parse(localStorage.getItem('chat:' + userStore.userProfile.id + '-' + route.params.id)) || [];
        ws = connect();
        nextTick(() => {
            chatBox.value.scrollTo({
                top: chatBox.value.scrollHeight
            });
        });
    }
}, { immediate: true });
</script>
<template>
    <div v-if='userStore.signed' w='md:720px' p='x-10px' text-regular margin-center>
        <typography color='primary'>
            <p text='20px'>
                职位咨询
            </p>
        </typography>
        <div rounded='2' p='4' bg-container border shadow-box>
            <div p='2' flex-between-x>
                <v-btn @click='router.go(-1)' color='transparent' flat icon>
                    <i text-info i-material-symbols-keyboard-backspace-rounded />
                </v-btn>
                <div text='18px' text-secondary>
                    <span>与 </span>
                    <span text-strong>{{ receiver.nickname }}</span>
                    <span> 的聊天</span>
                </div>
            </div>
            <div ref='chatBox' h='380px' overflow='auto' p='x-2' m='y-2' bg-fill border>
                <template v-for='(i,index) in chats' :key='index'>
                    <sender :content='i.content' v-if='i.sender===userStore.userProfile.id' />
                    <receiver :avatar='receiver.avatar' :content='i.content' v-else />
                </template>
            </div>
            <v-textarea rows='3' placeholder='在此输入内容...' v-model='content' variant='outlined' />
            <div text-end>
                <v-btn @click='send' bg-primary flat>
                    <span text-light>发送</span>
                </v-btn>
            </div>
        </div>
    </div>
</template>