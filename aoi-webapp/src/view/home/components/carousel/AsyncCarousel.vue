<script setup>
import { sleep } from '../../../../utils/time.js';

await sleep(700);
</script>
<template>
    <div class='carousel' h='!lt-md:400px' data-aos='animate__fadeIn'>
        <v-carousel height='100%' :show-arrows='false' delimiter-icon='i-material-symbols-cloud' rounded='2'
                    interval='4000' hide-delimiter-background cycle>
            <v-carousel-item>
                <div h='full' bg-primary></div>
            </v-carousel-item>
            <v-carousel-item>
                <div h='full' bg-success></div>
            </v-carousel-item>
            <v-carousel-item>
                <div h='full' bg-warning></div>
            </v-carousel-item>
            <v-carousel-item>
                <div h='full' bg-danger></div>
            </v-carousel-item>
        </v-carousel>
    </div>
</template>
<style scoped>
.carousel {
    height: calc(100vh - 54px - 68px);
    padding-bottom: 30px;
    min-height: 400px;
}

:deep(.i-material-symbols-cloud) {
    color: var(--c-disable) !important;
    font-size: 18px;
}

.v-carousel__controls button {
    margin: 0 3px !important;
}

:deep(.v-carousel__controls button:hover .i-material-symbols-cloud ) {
    color: var(--c-info) !important;
}
</style>