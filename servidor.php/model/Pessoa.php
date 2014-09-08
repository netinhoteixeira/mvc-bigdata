<?php

namespace Model;

use Doctrine\ODM\MongoDB\Mapping\Annotations as MongoDB;

/**
 * @MongoDB\Document
 * @MongoDB\HasLifecycleCallbacks
 */
class Pessoa implements \JsonSerializable
{

    /** @MongoDB\Id */
    private $id;

    /** @MongoDB\Date */
    private $cadastro;

    /** @MongoDB\String */
    private $nome;

    /** @MongoDB\Date */
    private $nascimento;

    /** @MongoDB\NotSaved */
    private $idade;

    /** @MongoDB\PrePersist */
    public function definirCadastro()
    {
        $this->cadastro = new \DateTime();
    }

    /**
     * @MongoDB\PreLoad
     */
    public function preLoad(array &$data)
    {
        // ao carregar, calcula a idade
        if (!is_null($data['nascimento'])) {
            $date = new \DateTime(date('Y-M-d H:i:s', $data['nascimento']->sec));
            $now = new \DateTime();
            $interval = $now->diff($date);

            $data['idade'] = $interval->y;
        }
    }

    /**
     * @MongoDB\PostUpdate
     */
    public function postUpdate()
    {
        // após a atualização, calcula a nova idade
        if (!is_null($this->nascimento)) {
            $date = $this->nascimento;
            $now = new \DateTime();
            $interval = $now->diff($date);

            $this->idade = $interval->y;
        }
    }

    public function getId()
    {
        return $this->id;
    }

    public function getCadastro()
    {
        return $this->cadastro;
    }

    public function getNome()
    {
        return $this->nome;
    }

    public function getNascimento()
    {
        return $this->nascimento;
    }

    public function getIdade()
    {
        return $this->idade;
    }

    public function setNome($nome)
    {
        $this->nome = $nome;
    }

    public function setNascimento($nascimento)
    {
        $this->nascimento = $nascimento;
    }

    public function jsonSerialize()
    {
        return array(
            'id' => $this->id,
            'cadastro' => (!is_null($this->cadastro)) ? $this->cadastro->format('Y-m-d') : null,
            'nome' => $this->nome,
            'nascimento' => (!is_null($this->nascimento)) ? $this->nascimento->format('Y-m-d') : null,
            'idade' => $this->idade
        );
    }

}
