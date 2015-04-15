'use strict';
angular.module('auctionApp')
.controller('PaymentController', ['$scope', '$timeout', '$routeParams', 'BidderResource', 'PaymentResource', 'InvoiceResource',
   function($scope, $timeout, $routeParams, BidderResource, PaymentResource, InvoiceResource) {
      $scope.bidderId = $routeParams.bidderId;
      $scope.getBidder = function() {
         if ( !$scope.bidderId ) return;
         $scope.bidder = BidderResource.get({bidderId:$scope.bidderId},
         function(bidder) {
            $scope.bidderId = bidder.id;
            $('#method').focus();
         },
         function(response) {
            $scope.error=response.data.exception;
            $('#bidderId').focus();
         });
         $scope.invoice = InvoiceResource.query({bidderId:$scope.bidderId},
         function(invoice) {
            $scope.computeBalance();
            $('#method').focus();
         },
         function(response) {
            $scope.error=response.data.exception;
             $timeout(function() {
                $('#bidderId').focus();
             }, 100);
         });
      }

      $scope.computeBalance = function() {
         $scope.balance = 0;
         $scope.invoice.forEach( function(line) {
            $scope.balance += line.amount;
         });
      }

      $scope.clearBidder = function() {
         $scope.bidder = null;
         $scope.reference = null;
         $scope.amount = null;
         $scope.error = null;
         $scope.invoice = null;
      }

      $scope.addPayment = function() {
         if ( !$scope.bidderId || !$scope.amount ) return;
         $scope.payment = new PaymentResource({bidderId:$scope.bidderId, method:$scope.method, reference:$scope.reference, amount:$scope.amount});
         $scope.payment.$save({}, function(payment) {
            $scope.getBidder();
            $timeout(function() {
               $('#bidderId').focus();
            }, 100);
         }, function(response) {
            $scope.error = response.data.exception;
         });
      }

      $scope.allowPayments = function() {
         return true;
      }
      $scope.getBidder();
   }
]);