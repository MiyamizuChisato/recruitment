import router from '../router/index.js';

export const go = async (path) => {
    if (path !== null && path !== '')
        await router.push(path);
};