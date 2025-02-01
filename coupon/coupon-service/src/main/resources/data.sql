INSERT INTO coupons (title, coupon_type, total_quantity, issued_quantity, discount_amount, min_available_amount, date_issued_start, date_issued_end)
VALUES
    ('Summer Sale Coupon', 'FIFO', 1000, 100, 10, 20000, '2025-02-01 10:00:00', '2025-02-10 10:00:00'),
    ('Winter Promo Coupon', 'FIFO', 500, 50, 20, 30000, '2025-02-01 10:00:00', '2025-06-12 10:00:00'),
    ('Black Friday Coupon', 'FIFO', 2000, 300, 15, 40000, '2025-01-13 10:00:00', '2025-04-20 10:00:00'),
    ('Spring Savings Coupon', 'FIFO', 800, 200, 25, 50000, '2025-04-13 10:00:00', '2025-05-28 10:00:00'),
    ('Holiday Promotion Coupon', 'FIFO', 1500, 400, 20, 60000, '2025-04-13 10:00:00', '2025-07-12 10:00:00');