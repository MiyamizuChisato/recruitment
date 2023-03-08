import gfm from '@bytemd/plugin-gfm';
import gemoji from '@bytemd/plugin-gemoji';
import highlight from '@bytemd/plugin-highlight';
import mediumZoom from '@bytemd/plugin-medium-zoom';
import frontmatter from '@bytemd/plugin-frontmatter';
import imageCenter from '../plugin/bytemdPluginImageCenter.js';
import modifyHrefTarget from '../plugin/bytemdPluginLink.js';

export const forEachMarkdownNode = (fun) => {
    document.querySelector('.markdown-body').childNodes.forEach(fun);
};
export const forEachMarkdownTitle = (fun) => {
    document.querySelector('.markdown-body').querySelectorAll('h1,h2').forEach(fun);
};
export const getViewerPlugins = () => {
    return [gfm(), gemoji(), highlight(), mediumZoom(), frontmatter(), imageCenter(), modifyHrefTarget()];
};