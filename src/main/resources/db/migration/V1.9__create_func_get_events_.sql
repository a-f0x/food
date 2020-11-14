DROP FUNCTION IF EXISTS get_events(_user_id INT, _start_user_time TIMESTAMP, _end_user_time TIMESTAMP);
CREATE FUNCTION get_events(_user_id INT, _start_user_time TIMESTAMP, _end_user_time TIMESTAMP)
    RETURNS TABLE
            (
                grouped_by_hours TIMESTAMP,
                e_id             INT,
                e_type           VARCHAR(64),
                e_name           VARCHAR(255),
                e_kcal           NUMERIC(10, 2),
                e_utime          TIMESTAMP,
                food_weight_gram NUMERIC(10, 2),
                food_id          INT,
                food_name        VARCHAR(255),
                protein          NUMERIC(10, 2),
                fat              NUMERIC(10, 2),
                carb             NUMERIC(10, 2),
                food_kcal        NUMERIC(10, 2)
            )
AS
$func$
BEGIN
    RETURN QUERY
        SELECT date_trunc('hour', user_time) AS grouped_by_hours,
               events.id                     AS e_id,
               event_type                    AS e_type,
               events.name                   AS e_name,
               events.k_cal                  AS e_kcal,
               user_time                     AS e_utime,
               f_events.weight_gram          AS food_weight_gram,
               f.id                          AS food_id,
               f.name                        AS food_name,
               f.protein                     AS protein,
               f.fat                         AS fat,
               f.carb                        AS carb,
               f.k_cal                       as food_kcal


        FROM events
                 LEFT OUTER JOIN food_events f_events ON food_event_id = f_events.id
                 LEFT JOIN food f ON f_events.food_id = f.id

        WHERE is_removed = FALSE
          AND user_id = _user_id
          AND user_time >= _start_user_time
          AND user_time <= _end_user_time

        GROUP BY e_id,
                 grouped_by_hours,
                 event_type,
                 e_name,
                 e_kcal,
                 user_time,
                 f_events.id,
                 f.id
        ORDER BY grouped_by_hours;

END


$func$ LANGUAGE plpgsql;