'use strict';
angular.module('auctionApp')
.controller('WinningBidController', ['$scope', '$timeout', 'ItemResource', 'BidderResource', 'BidResource',
   function($scope, $timeout, ItemResource, BidderResource, BidResource) {
      $('#itemId').focus();

      $scope.getItem = function() {
         if ( !$scope.itemId ) return;
         $scope.item = ItemResource.get({itemId:$scope.itemId},
         function(item) {
            $('#bidderId').focus();
         },
         function(response) {
            $scope.error=response.data.exception;
            $('#itemId').focus();
         });
      }

      $scope.clearItem = function() {
         $scope.item = null;
         $scope.error = null;
      }

      $scope.getBidder = function() {
         if ( !$scope.bidderId ) return;
         $scope.bidder = BidderResource.get({bidderId:$scope.bidderId},
         function(bidder) {
            $('#amount').focus();
         },
         function(response) {
            $scope.error=response.data.exception;
            $('#bidderId').focus();
         });
      }

      $scope.clearBidder = function() {
         $scope.amount = null;
         $scope.bidder = null;
         $scope.error = null;
      }

      $scope.addBid = function() {
         if ( !$scope.bidderId || !$scope.amount ) return;
         $scope.bid = new BidResource({itemId:$scope.itemId, bidderId:$scope.bidderId, amount:$scope.amount});
         $scope.bid.$save({}, function(bid) {
            $scope.item.winningBids.push(bid)
            $scope.clearBidder();
            $scope.bidderId = null;
         }, function(response) {
            $scope.error = response.data.exception;
         });
         $timeout(function() {
            $('#bidderId').focus();
         }, 100);
      }

      $scope.allowBids = function() {
         if (!$scope.item || !$scope.item.winningBids) return true;
         return $scope.item && $scope.item.winningBids.length < $scope.item.maxWinners
      }
   }
]);
