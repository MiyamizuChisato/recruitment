import 'uno.css';
import 'animate.css';
import 'normalize.css';
import 'vuetify/styles';
import '../assets/style/index.css';
import { createVuetify } from 'vuetify';
import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';
import 'bytemd/dist/index.css';
import 'juejin-markdown-themes/dist/juejin.min.css';
const vuetify = createVuetify({
    components,
    directives,
    display: {
        mobileBreakpoint: 'sm',
        thresholds: {
            xs: 0,
            sm: 640,
            md: 768,
            lg: 1024,
            xl: 1280
        }
    }
});
export default vuetify;