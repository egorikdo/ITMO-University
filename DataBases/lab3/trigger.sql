CREATE TRIGGER character_update_character_memories
AFTER INSERT OR UPDATE OR DELETE ON Memory
FOR EACH ROW
EXECUTE FUNCTION update_character_memories();
