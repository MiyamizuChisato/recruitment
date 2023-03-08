<script setup>
import { go } from '../../../utils/router.js';
import { useUserStore } from '../../../store/userStore.js';
import { useToast } from 'vue-toastification';
import { validateRelation } from '../../../api/relation.js';

const props = defineProps({
    card: Object
});
const userStore = useUserStore();
const toast = useToast();
const toDetail = () => {
    go('/detail/' + props.card.id);
};
const toChat = async () => {
    if (!userStore.signed) {
        toast.info('请先登陆');
        return;
    }
    await validateRelation(userStore.userProfile.id, props.card.user.id);
    if (userStore.userProfile.id !== props.card.user.id) {
        await go('/chat/' + props.card.user.id);
    } else {
        toast.warning('不能向自己发起聊天');
    }
};
</script>
<template>
    <div @click='toDetail' overflow='hidden' cursor='default' rounded='2'
         bg-container border shadow-box text-regular v-ripple>
        <div p='x-4 y-2' flex-between-x>
            <div font='700' text-strong>{{ card.title }}</div>
            <div text-danger>12-15K</div>
        </div>
        <div p='x-4 b-2' flex-between-x>
            <div flex-center>
                <v-avatar rounded size='36' :image='card.company.logo' />
                <div text='14px' m='l-2'>{{ card.company.name }}</div>
                <div m='x-1'>·</div>
                <div text='14px' text-holder>{{ card.company.location }}</div>
            </div>
            <div text-info flex-center>
                <i m='r-2' i-heroicons-solid-lightning-bolt />
                <div>{{ card.hot }}</div>
            </div>
        </div>
        <div p='x-4 y-2' bg-fill flex-between-x>
            <div flex-center>
                <v-avatar size='36' :image='card.user.avatar' />
                <div m='x-2'>
                    {{ card.user.nickname }}
                </div>
            </div>
            <v-btn @click.stop='toChat' color='transparent' size='28' flat>
                <i text='5' cursor='pointer' text-primary i-material-symbols-chat-rounded />
            </v-btn>
        </div>
    </div>
</template>