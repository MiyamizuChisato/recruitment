import { presetAttributify, presetIcons, presetUno } from 'unocss';
import UnocssIcons from '@unocss/preset-icons';

export const rules = [
    ['position-center', {
        'position': 'absolute',
        'left': '50%',
        'top': '50%',
        'transform': 'translateX(-50%) translateY(-50%)'
    }],
    [/^line-(\d+)$/, ([, l]) => {
        if (~~l === 1) {
            return {
                'overflow': 'hidden',
                'text-overflow': 'ellipsis',
                'white-space': 'nowrap',
                'width': '100%'
            };
        }
        return {
            'overflow': 'hidden',
            'display': '-webkit-box',
            '-webkit-box-orient': 'vertical',
            '-webkit-line-clamp': l
        };
    }],
    ['flex-y', {
        'display': 'flex',
        'flex-direction': 'column'
    }],
    ['shadow-box', {
        'box-shadow': 'var(--c-shadow)'
    }],
    ['space-pre', {
        'white-space': 'pre-wrap'
    }]
];
export const theme = {
    colors: {
        primary: {
            light: '#366ef4',
            dark: '#366ef4'
        },
        success: {
            light: '#008858',
            dark: '#008858'
        },
        warning: {
            light: '#be5a00',
            dark: '#be5a00'
        },
        danger: {
            light: '#d54941',
            dark: '#d54941'
        },
        info: {
            light: '#a6a6a6',
            dark: '#a6a6a6'
        },
        strong: {
            light: '#303133',
            dark: '#E5EAF3'
        },
        regular: {
            light: '#606266',
            dark: '#CFD3DC'
        },
        secondary: {
            light: '#909399',
            dark: '#A3A6AD'
        },
        holder: {
            light: '#A8ABB2',
            dark: '#8D9095'
        },
        disable: {
            light: '#bdb7b7',
            dark: '#5f5f5f'
        },
        screen: {
            light: '#f1f3f4',
            dark: '#202124'
        },
        fill: {
            light: '#f9fafb',
            dark: '#3c4042'
        },
        container: {
            light: '#FFFFFF',
            dark: '#35363a'
        },
        border: {
            light: '#DCDFE6',
            dark: '#4C4D4F'
        },
        hover: {
            light: '#e7e4e4',
            dark: '#4f4f4f'
        },
        selected: {
            light: '#eae8e800',
            dark: '#646464'
        }
    }
};
export const presets = [
    presetUno(),
    presetAttributify(),
    presetIcons(),
    UnocssIcons({
        prefix: 'i-',
        extraProperties: {
            'display': 'block',
            'font-size': '1.5rem'
        }
    })
];
export const shortcuts = [
    {
        'text-primary': '!text-primary-light !dark:text-primary-dark',
        'text-success': '!text-success-light !dark:text-success-dark',
        'text-warning': '!text-warning-light !dark:text-warning-dark',
        'text-danger': '!text-danger-light !dark:text-danger-dark',
        'text-info': '!text-info-light !dark:text-info-dark',
        'text-strong': '!text-strong-light !dark:text-strong-dark',
        'text-regular': '!text-regular-light !dark:text-regular-dark',
        'text-secondary': '!text-secondary-light !dark:text-secondary-dark',
        'text-holder': '!text-holder-light !dark:text-holder-dark',
        'text-disable': '!text-disable-light !dark:text-disable-dark',
        'text-light': '!text-light-50',
        'text-dark': '!text-dark-50'
    },
    {
        'text-small': 'text-12px',
        'text-middle': 'text-14px',
        'text-huge': 'text-17px',
        'text-title': 'text-20px'
    },
    {
        'bg-primary': '!bg-primary-light !dark:bg-primary-dark',
        'bg-success': '!bg-success-light !dark:bg-success-dark',
        'bg-strong': '!bg-strong-light !dark:bg-strong-dark',
        'bg-regular': '!bg-regular-light !dark:bg-regular-dark',
        'bg-secondary': '!bg-secondary-light !dark:bg-secondary-dark',
        'bg-holder': '!bg-holder-light !dark:bg-holder-dark',
        'bg-disable': '!bg-disable-light !dark:bg-disable-dark',
        'bg-warning': '!bg-warning-light !dark:bg-warning-dark',
        'bg-danger': '!bg-danger-light !dark:bg-danger-dark',
        'bg-info': '!bg-info-light !dark:bg-info-dark',
        'bg-screen': '!bg-screen-light !dark:bg-screen-dark',
        'bg-fill': '!bg-fill-light !dark:bg-fill-dark',
        'bg-container': '!bg-container-light !dark:bg-container-dark',
        'bg-hover': '!bg-hover-light !dark:bg-hover-dark'
    },
    {
        'border-primary': '!border-primary-light !dark:border-primary-dark !border-1 !border-solid',
        'border-success': '!border-success-light !dark:border-success-dark !border-1 !border-solid',
        'border-warning': '!border-warning-light !dark:border-warning-dark !border-1 !border-solid',
        'border-danger': '!border-danger-light !dark:border-danger-dark !border-1 !border-solid',
        'border-info': '!border-info-light !dark:border-info-dark !border-1 !border-solid',
        'border': '!border-border-light !dark:border-border-dark !border-1 !border-solid',
        'border-c-primary': '!border-primary-light !dark:border-primary-dark'
    },
    {
        'flex-center': '!display-flex !justify-center !items-center',
        'flex-center-x': '!display-flex !items-center',
        'flex-center-y': '!display-flex !justify-center',
        'flex-between-x': '!display-flex !justify-between !items-center',
        'flex-between-y': '!flex-between-x !flex-col',
        'flex-start': '!display-flex !justify-start !items-center',
        'flex-end': '!display-flex !justify-end !items-center'
    },
    {
        'shadow': 'shadow ',
        'margin-center': '!display-block !m-x-auto',
        'img-center': '!display-block !m-x-auto ',
        'strong': 'text-strong text-20px',
        'pointer': '!cursor-pointer'
    },
    // 页面响应
    {
        'w-gt-xl': '!xl-w-1180px',
        'w-lt-xl': '!lt-xl-w-750px',
        'w-lt-md': '!lt-md-w-100%',
        'p-x-lt-md': '!lt-md-p-x-2',
        'page': 'w-gt-xl w-lt-xl w-lt-md margin-center'
    }
];