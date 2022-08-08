package com.fdmgroup.bookstore.data;

import com.fdmgroup.bookstore.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class UserArrayListRepository implements UserRepository
{
    private List<User> users;
    private int id;
    private int generateId = 1;

    public UserArrayListRepository(List<User> users)
    {
        this.users = users;
    }

    public UserArrayListRepository()
    {
    }

    @Override
    public <S extends User> S save(S user)
    {
        if (user.getUserId() >= 1) return modify(user);
        else
        {
            final User byUsername = findByUsername(user.getUsername());
            if (byUsername == null)
            {
                if (passwordValidation(user.getPassword()))
                {
                    user.setUserId(++generateId);

                    users.add(user);
                    return user;
                }
            }
            else throw new RuntimeException("this username is exists => " + user.getUsername());
        }

        return null;
    }

    public synchronized <S extends User> S modify(S user)
    {
        final User byUsername = findByUsername(user.getUsername());
        if (byUsername == null || byUsername.getUserId() == user.getUserId())
        {
            if (passwordValidation(user.getPassword()))
            {
                final User byId = findById(user.getUserId());

                byId.setFirstName(user.getFirstName());
                byId.setLastName(user.getLastName());
                byId.setUsername(user.getUsername());
                byId.setPassword(user.getPassword());
                byId.setOrders(user.getOrders());
                byId.setEmail(user.getEmail());

                final int indexById = findIndexById(user.getUserId());

                users.set(indexById , byId);

                return (S) byId;
            }
        }
        else throw new RuntimeException("this username is exists => " + user.getUsername());

        return null;
    }


    private boolean passwordValidation(final String password)
    {
        if (password == null || password.isEmpty())
            throw new RuntimeException("Password is null");

        return true;
    }

    @Override
    public <S extends User> List<S> saveAll(Iterable<S> entities)
    {
        final List<User> users = new ArrayList<>();
        for (final S entity : entities) users.add(save(entity));

        return (List<S>) users;
    }

    @Override
    public User save()
    {
        return null;
    }

    @Override
    public User findById(int id)
    {
        return users.stream().filter(user -> user.getUserId() == id).findAny().orElse(null);
    }

    public int findIndexById(int id)
    {
        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getUserId() == id) return i;
        }
        return -1;
    }

    @Override
    public boolean validate(String username , String password)
    {
        final User find = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .filter(user -> user.getPassword().equals(password))
                .findAny()
                .orElse(null);

        return find != null;
    }

    @Override
    public User findByUsername(String username)
    {
        return users.stream().filter(user -> user.getUsername().equals(username)).findAny().orElse(null);
    }

    @Override
    public List<User> findAll()
    {
        return users;
    }

    @Override
    public List<User> findAll(Sort sort)
    {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable pageable)
    {
        return null;
    }

    @Override
    public List<User> findAllById(Iterable<Integer> integers)
    {
        final List<User> users = new ArrayList<>();
        integers.forEach(id -> findById(id).ifPresent(users::add));
        return users;
    }

    @Override
    public long count()
    {
        return users.size();
    }

    @Override
    public void deleteById(Integer integer)
    {
        final int indexById = findIndexById(integer);
        if (indexById >= 0) users.remove(indexById);
    }

    @Override
    public void delete(User entity)
    {
        if (entity.getUserId() >= 1) deleteById(entity.getUserId());
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers)
    {
        integers.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends User> entities)
    {
        entities.forEach(user ->
        {
            if (user.getUserId() >= 1 && findById(user.getUserId()) != null)
                deleteById(user.getUserId());
        });
    }

    @Override
    public void deleteAll()
    {
        users.clear();
    }

    @Override
    public Optional<User> findById(Integer integer)
    {
        final User find = users.stream().filter(user -> user.getUserId() == integer).findAny().orElse(null);
        return Optional.ofNullable(find);
    }

    @Override
    public boolean existsById(Integer integer)
    {
        return findById(integer).isPresent();
    }

    @Override
    public void flush()
    {

    }

    @Override
    public <S extends User> S saveAndFlush(S entity)
    {
        return null;
    }

    @Override
    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities)
    {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<User> entities)
    {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers)
    {

    }

    @Override
    public void deleteAllInBatch()
    {

    }

    @Override
    public User getOne(Integer integer)
    {
        final Optional<User> byId = findById(integer);
        return byId.orElse(null);
    }

    @Override
    public User getById(Integer integer)
    {
        return getOne(integer);
    }

    @Override
    public User getReferenceById(Integer integer)
    {
        return getOne(integer);
    }

    @Override
    public <S extends User> Optional<S> findOne(Example<S> example)
    {
        return Optional.empty();
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example)
    {
        return null;
    }

    @Override
    public <S extends User> List<S> findAll(Example<S> example , Sort sort)
    {
        return null;
    }

    @Override
    public <S extends User> Page<S> findAll(Example<S> example , Pageable pageable)
    {
        return null;
    }

    @Override
    public <S extends User> long count(Example<S> example)
    {
        return 0;
    }

    @Override
    public <S extends User> boolean exists(Example<S> example)
    {
        return false;
    }

    @Override
    public <S extends User, R> R findBy(Example<S> example , Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction)
    {
        return null;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getGenerateId()
    {
        return generateId;
    }

    public void setGenerateId(int generateId)
    {
        this.generateId = generateId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserArrayListRepository that = (UserArrayListRepository) o;
        return id == that.id && generateId == that.generateId && Objects.equals(users , that.users);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(users , id , generateId);
    }

    @Override
    public String toString()
    {
        return "UserArrayListRepository{" +
                "users=" + users +
                ", id=" + id +
                ", generateId=" + generateId +
                '}';
    }
}
