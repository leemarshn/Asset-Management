CREATE OR REPLACE FUNCTION get_user_session_id()
    RETURNS VARCHAR(255) AS $$
DECLARE
    current_user_id BIGINT := 123; -- Replace this with the current user's ID (Assuming you know it)
    user_session_id VARCHAR(255);
BEGIN
    SELECT session_id INTO user_session_id
    FROM user_session_info
    WHERE user_id = current_user_id
      AND status = 'ACTIVE' -- Add any relevant criteria to identify the correct user session
    ORDER BY creation_time DESC
    LIMIT 1;

    RETURN user_session_id;
END;
$$ LANGUAGE plpgsql;
