package com.stripe.functional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.stripe.BaseStripeTest;
import com.stripe.exception.StripeException;
import com.stripe.model.Review;
import com.stripe.model.ReviewCollection;
import com.stripe.net.ApiResource;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ReviewTest extends BaseStripeTest {
  public static final String REVIEW_ID = "prv_123";

  private Review getReviewFixture() throws StripeException {
    final Review review = Review.retrieve(REVIEW_ID);
    resetNetworkSpy();
    return review;
  }

  @Test
  public void testApprove() throws StripeException {
    final Review review = getReviewFixture();

    review.approve();
    assertNotNull(review);
    verifyRequest(
        ApiResource.RequestMethod.POST,
        String.format("/v1/reviews/%s/approve", review.getId())
    );
  }

  @Test
  public void testList() throws StripeException {
    final Map<String, Object> params = new HashMap<String, Object>();
    params.put("limit", 1);

    final ReviewCollection reviews = Review.list(params);

    assertNotNull(reviews);
    verifyRequest(
        ApiResource.RequestMethod.GET,
        String.format("/v1/reviews")
    );
  }

  @Test
  public void testRetrieve() throws StripeException {
    final Review review = Review.retrieve(REVIEW_ID);

    assertNotNull(review);
    verifyRequest(
        ApiResource.RequestMethod.GET,
        String.format("/v1/reviews/%s", review.getId())
    );
  }
}
