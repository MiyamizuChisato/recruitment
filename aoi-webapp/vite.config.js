import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import Unocss from 'unocss/vite';
import { theme, shortcuts, presets, rules } from './unocss.config';

export default defineConfig({
    plugins: [
        vue(),
        Unocss({
            theme, shortcuts, presets, rules
        })
    ],
    server: {
        port: 80,
        proxy: {
            '/aoi': {
                changeOrigin: true,
                target: 'http://localhost:8080',
            }
        }
    }
});
