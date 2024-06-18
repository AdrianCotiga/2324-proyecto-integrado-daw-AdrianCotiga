import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        loadComponent: () => import('./components/login/login.component')
            .then(m => m.LoginComponent)
    },
    {
        path: 'signup',
        loadComponent: () => import('./components/register/register.component')
            .then(m => m.RegisterComponent)
    },
    {
        path: 'profile',
        loadComponent: () => import('./components/profile/profile.component')
            .then(m => m.ProfileComponent)
    },
    {
        path: 'admin/users',
        loadComponent: () => import('./components/users/users.component')
            .then(m => m.UsersComponent)
    },
    {
        path: 'admin/users/:id/edit',
        loadComponent: () => import('./components/user-crud/user-crud.component')
            .then(m => m.UserCrudComponent)
    },
    {
        path: 'admin/users/:id/delete',
        loadComponent: () => import('./components/user-crud/user-crud.component')
            .then(m => m.UserCrudComponent)
    },
    {
        path: 'admin/users/:id/detail',
        loadComponent: () => import('./components/profile/profile.component')
            .then(m => m.ProfileComponent)
    },
];
