<script setup>
import Typography from '../../components/Typography.vue';
import PositionCard from './component/PositionCard.vue';
import { onMounted, ref, watch } from 'vue';
import { getCardPage } from '../../api/position.js';
import { onApiSuccess } from '../../utils/assert.js';
import { useAppStore } from '../../store/appStore.js';
import { go } from '../../utils/router.js';

const appStore = useAppStore();
const page = ref({
    current: 1
});
const getCard = async () => {
    const { code, data } = await getCardPage(page.value.current);
    onApiSuccess(code, () => {
        page.value = data;
    });
};
watch(() => page.value.current, async () => {
    await getCard();
    window.scrollTo({
        behavior: 'smooth',
        top: 0
    });
});
const key = ref('');

const search = () => {
    go('/search/' + key.value);
};
onMounted(getCard);
</script>
<template>
    <div p='x-8px' text-regular v-if='page'>
        <typography color='primary'>
            <p text='20px'>搜索职位</p>
        </typography>
        <v-text-field v-model='key' :theme='appStore.theme' variant='solo'
                      append-inner-icon='i-material-symbols-search-rounded'
                      @click:append-inner='search' placeholder='请输入关键字搜索' single-line hide-details />
        <typography color='primary'>
            <p text='20px'>全部职位</p>
        </typography>
        <v-container>
            <v-row>
                <v-col v-for='i in page.records' lg='3' sm='6' cols='12'>
                    <position-card :card='i' />
                </v-col>
            </v-row>
        </v-container>
        <v-pagination prev-icon='i-material-symbols-arrow-back-ios-new-rounded'
                      next-icon='i-material-symbols-arrow-forward-ios-rounded'
                      v-model='page.current' :total-visible='3'
                      :length='page.pages' text-strong />
    </div>
</template>