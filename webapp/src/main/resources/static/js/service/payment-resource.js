'use strict';
angular.module('auctionApp')
.factory('PaymentResource', ['$resource',
function($resource) {
   return $resource('/api/payment', {}, {
   });
}]);