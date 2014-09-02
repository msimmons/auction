var auctionApp = angular.module('auctionApp', [
      'ngRoute',
      'ngResource',
      'auctionControllers',
      'auctionServices'
]);

auctionApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/bidders', {
        templateUrl: 'html/bidder-list.html',
        controller: 'BidderListController'
      }).
      when('/bidder/:bidderId', {
        templateUrl: 'html/bidder.html',
        controller: 'BidderController'
      }).
      when('/items', {
        templateUrl: 'html/item-list.html',
        controller: 'ItemListController'
      }).
      when('/item/:itemId', {
        templateUrl: 'html/item.html',
        controller: 'ItemController'
      }).
      when('/payment/:bidderId', {
        templateUrl: 'html/payment.html',
        controller: 'PaymentController'
      }).
      when('/winning-bids', {
        templateUrl: 'html/winning-bid.html',
        controller: 'WinningBidController'
      }).
      otherwise({
        redirectTo: '/bidders'
      });
  }]);