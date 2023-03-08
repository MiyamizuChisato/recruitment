<script setup>
import Carousel from './components/carousel/Carousel.vue';
import Typography from '../../components/Typography.vue';
import { onMounted, ref } from 'vue';
import { getCarousel } from '../../api/position.js';
import PositionCard from '../position/component/PositionCard.vue';

const list = ref([]);
onMounted(async () => {
    const { data } = await getCarousel();
    list.value = data;
});
</script>
<template>
    <div w='xl:1032px' p-x-lt-md margin-center>
        <typography color='primary'>
            <p text='20px'>就业刊物</p>
        </typography>
        <carousel />
        <typography color='primary'>
            <p text='20px'>热门职位</p>
        </typography>
        <v-row>
            <v-col v-for='i in list' lg='3' sm='6' cols='12'>
                <position-card :card='i' />
            </v-col>
        </v-row>
    </div>
</template>