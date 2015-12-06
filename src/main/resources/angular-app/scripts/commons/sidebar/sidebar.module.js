angular.module('entUbp.sidebar', [
    'eehNavigation',
    'pascalprecht.translate',
    'ui.bootstrap',
    'ui.router'
])
    .config(['eehNavigationProvider', function(eehNavigationProvider) {
        var isLoggedIn = function() {
            return true;
        };
        var isAdmin = function() {
            return isLoggedIn() && true;
        };

        eehNavigationProvider.iconBaseClass('fa');
        eehNavigationProvider.defaultIconClassPrefix('fa');

        eehNavigationProvider
            .menuItem('sidebar.home', {
                text: 'Acceuil',
                iconClass: 'fa-home fa-2',
                href: '/#/'
            })
            .menuItem('sidebar.classroom', {
                text: 'Salle de cours',
                iconClass: 'fa-bank',
                href: '/#/classroom',
                isVisible: isAdmin
            })
            .menuItem('sidebar.equipments-type', {
                text: 'Type d\'équipements',
                iconClass: 'fa-wrench',
                href: '/#/equipment-type',
                isVisible: isAdmin
            });

    }]);


