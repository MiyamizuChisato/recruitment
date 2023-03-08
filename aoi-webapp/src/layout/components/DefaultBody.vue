<template>
    <main page>
        <router-view v-slot='{ Component }'>
            <keep-alive>
                <component :key='$route.name' :is='Component' v-if='$route.meta.alive' />
            </keep-alive>
            <component :key='$route.name' :is='Component' v-if='!$route.meta.alive' />
        </router-view>
    </main>
</template>
<style scoped>
main {
    flex: 1;
}
</style>
